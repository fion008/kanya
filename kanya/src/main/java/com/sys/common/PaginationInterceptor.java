package com.sys.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.ibatis.cache.Cache;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.MappedStatement.Builder;
import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.mapping.ResultMapping;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

/**
 * 分页插件
 * @author andy
 *
 */
@Intercepts({ @Signature(type = Executor.class, method = "query", args = { MappedStatement.class, Object.class,
		RowBounds.class, ResultHandler.class }) })
public class PaginationInterceptor implements Interceptor {
	private final static int MAPPED_STATEMENT_INDEX = 0;
	private final static int PARAMETER_INDEX = 1;
	private final static int ROWBOUNDS_INDEX = 2;

	public Object intercept(Invocation invocation) throws Throwable {
		processIntercept(invocation.getArgs());
		return invocation.proceed();
	}

	private void processIntercept(final Object[] queryArgs) {
		MappedStatement ms = (MappedStatement) queryArgs[MAPPED_STATEMENT_INDEX];
		Object parameter = queryArgs[PARAMETER_INDEX];
		final RowBounds rowBounds = (RowBounds) queryArgs[ROWBOUNDS_INDEX];

		if(rowBounds instanceof PagingRounds){
			BoundSql boundSql = ms.getBoundSql(parameter);
			String sql = boundSql.getSql();
			sql += " limit " + rowBounds.getOffset() + "," + rowBounds.getLimit();
			sql = sql.replaceAll("\\s{2,}", " ");
			BoundSql newBoundSql = new BoundSql(ms.getConfiguration(), sql, boundSql.getParameterMappings(), boundSql.getParameterObject());
			MappedStatement newMs = copyFromMappedStatement(ms, new BoundSqlSqlSource(newBoundSql), null);
			queryArgs[MAPPED_STATEMENT_INDEX] = newMs;//重新设置sql
			queryArgs[ROWBOUNDS_INDEX] = RowBounds.DEFAULT;//关闭mybatis默认内存分页
		}else if (parameter instanceof CustomParameter) {
			CustomParameter customParameter = (CustomParameter) parameter;
			parameter = customParameter.getParameter();
			BoundSql boundSql = ms.getBoundSql(parameter);
			String sql = boundSql.getSql();
			if(customParameter.isCount()){
				sql = sql.replaceAll("\\s{2,}", " ").trim();
				sql = sql.replaceAll("\\s{2,}", " ").replace(" FROM", " from").replace("ORDER BY", "order by").replace("GROUP BY", "group by").trim();

				if (sql.split("from").length > 2 || sql.split("order by").length > 2 || sql.indexOf("group by") > -1) {
					sql = "select count(1) from (" + sql + ") _tmp";
				} else {
					int fromIndex = sql.indexOf(" from");
					sql = "select count(1)" + sql.substring(fromIndex);
					
					int orderByIndex = sql.indexOf("order by");
					if (orderByIndex > 0) {
						sql = sql.substring(0, orderByIndex);
					}
				}
			}
			BoundSql newBoundSql = new BoundSql(ms.getConfiguration(), sql, boundSql.getParameterMappings(), boundSql.getParameterObject());
			MappedStatement newMs = copyFromMappedStatement(ms, new BoundSqlSqlSource(newBoundSql), customParameter.getReturnClazz());
			queryArgs[MAPPED_STATEMENT_INDEX] = newMs;//重新设置sql
			queryArgs[PARAMETER_INDEX] = parameter;
		}
	}

	private class BoundSqlSqlSource implements SqlSource {
		private BoundSql boundSql;

		private BoundSqlSqlSource(BoundSql boundSql) {
			this.boundSql = boundSql;
		}

		public BoundSql getBoundSql(Object parameterObject) {
			return boundSql;
		}
	}
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	public void setProperties(Properties properties) {

	}

	private MappedStatement copyFromMappedStatement(MappedStatement ms, SqlSource newSqlSource, Class<?> newClass) {
		Builder builder = new Builder(ms.getConfiguration(), ms.getId(), newSqlSource, ms.getSqlCommandType());
		builder.resource(ms.getResource());
		builder.fetchSize(ms.getFetchSize());
		builder.statementType(ms.getStatementType());
		builder.keyGenerator(ms.getKeyGenerator());
		String[] s = ms.getKeyProperties();
		if (s == null) {
			builder.keyProperty(null);
		} else {
			builder.keyProperty(s[0]);
		}
		builder.timeout(ms.getTimeout());
		builder.parameterMap(ms.getParameterMap());
		if (newClass != null) {////重新设置sql返回类型
			List<ResultMap> resultMaps = new ArrayList<ResultMap>();
			resultMaps.add(new ResultMap.Builder(ms.getConfiguration(), ms.getId(), newClass, new ArrayList<ResultMapping>()).build());
			builder.resultMaps(resultMaps);
		} else {
			builder.resultMaps(ms.getResultMaps());
		}
		
		Cache cache = ms.getCache();//是否使用cache保持一致
		if (cache != null) {
			builder.cache(cache);
		}
		builder.useCache(ms.isUseCache());
		
		return builder.build();
	}
}
