package com.sys.baseutil;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


public class JspToHtml {
	private static String title = "标题测试";
	private static String context = "标题测试";
	private static String templateFilePath = System.getProperty("user.dir") + "\\src\\local\\spring\\TemplateHelp.txt";

	/**
	 * 根据本地模板生成静态页面
	 * 
	 * @param JspFile
	 *            jsp路经
	 * @param HtmlFile
	 *            html路经
	 * @return
	 */
	public static boolean JspToHtmlFile(String tablename, String fileName, String title, Map<String, String> fields, String HtmlFile) {
		String url = System.getProperty("user.dir") + "\\WebRoot\\2fm\\" + fileName;
		long beginDate = (new Date()).getTime();
		String str = "";
		try {
			String tempStr = "";
			FileInputStream is = new FileInputStream(url);// 读取模块文件
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			while ((tempStr = br.readLine()) != null)
				str = str + tempStr;
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		try {

			str = str.replaceAll("##title##", title);
			StringBuilder sb = new StringBuilder();
			sb.append("<form action=\"" + tablename + ".do?method=save" + getClassName(tablename) + "\" method=\"post\" name=\"f1\" id=\"f1\">\n");
			sb.append("<table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"1\">\n");
			sb.append("<TBODY>\n");
			Iterator i = fields.entrySet().iterator();
			while (i.hasNext()) {
				Map.Entry me = (Map.Entry) i.next();
				String key = me.getKey().toString().toLowerCase();
				String key2 = key.substring(0, 1).toUpperCase() + key.substring(1);
				String value = me.getValue().toString();
				if (key == "id") {
					sb.append("<input type=\"hidden\" name=\"id\" id=\"id\"/>\n");
				} else {
					sb.append("<TR height=30 bgColor=#ffffff >\n");
					sb.append("<TD width=\"40%\"  height=32 align=right valign=\"middle\">" + value + "：</TD>\n");
					sb.append("<td style=\"padding-left:5px;\"><input type=\"text\" id=" + key + " name=" + key + " value=\"\"  style=\"width:250px;\" /></td>\n");
					sb.append("<tr>\n");
				}
			}
			sb.append(" </table>\n");
			sb.append("<div style=\"padding-top:10px;\"><center><INPUT type=\"submit\" id=\"button2\" value=\"保 存\">&nbsp;<INPUT type=\"reset\" id=\"button2\" value=\"重 置\">&nbsp;</center></div>\n");
			sb.append("</form>\n");
			str = str.replaceAll("##content##", sb.toString());
			File f = new File(System.getProperty("user.dir") + "\\WebRoot\\" + HtmlFile + "\\" + tablename + "_add.jsp");
			BufferedWriter o = new BufferedWriter(new FileWriter(f));
			o.write(str);
			o.close();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 根据本地模板生成静态页面
	 * 
	 * @param JspFile
	 *            jsp路经
	 * @param HtmlFile
	 *            html路经
	 * @return
	 */
	public static boolean JspToHtmlFile2(String tablename, String fileName, String title, Map<String, String> fields, String HtmlFile) {
		String url = System.getProperty("user.dir") + "\\WebRoot\\2fm\\" + fileName;
		long beginDate = (new Date()).getTime();
		String str = "";
		try {
			String tempStr = "";
			FileInputStream is = new FileInputStream(url);// 读取模块文件
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			while ((tempStr = br.readLine()) != null)
				str = str + tempStr;
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		try {

			str = str.replaceAll("##title##", title);
			StringBuilder sb = new StringBuilder();
			sb.append("<form action=\"" + tablename + ".do?method=get" + getClassName(tablename) + "\" method=\"post\" name=\"f1\" id=\"f1\">\n");
			sb.append("<table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"1\">\n");
			sb.append("<tr>\n<td>\n<TABLE cellSpacing=1 cellPadding=0 width=\"100%\" align=\"center\" bgColor=#186900 border=0 >\n");
			sb.append("<TR bgcolor=#ffffff>\n");

			Iterator i = fields.entrySet().iterator();
			while (i.hasNext()) {
				Map.Entry me = (Map.Entry) i.next();
				String key = me.getKey().toString().toLowerCase();
				String key2 = key.substring(0, 1).toUpperCase() + key.substring(1);
				String value = me.getValue().toString();
				if (key == "id") {
					// sb.append("<input type=\"hidden\" name=\"id\" id=\"id\"/>\n");
				} else {
					sb.append("<TD align=\"right\" height=\"27\" width=\"8%\">" + value + "：</TD>\n");
					sb.append("<td style=\"padding-left:5px;\"><input type=\"text\" id=" + key + " name=" + key + " value=\"\"  style=\"width:150px;\" /></td>\n");
				}
			}
			sb.append(" </td>\n</table>\n</td>\n</tr>\n");
			sb.append("<tr>\n<td valign=\"top\">\n<div id=\"search_list\"></div>\n</td>\n</tr>\n");
			sb.append("<tr>\n<td>\n<iframe src=\"\" name=\"if1\" id=\"if1\" border=0 width=\"98%\" height=\"30\" align=\"center\" frameborder=\"no\" border=\"0\" marginwidth=\"0\" marginheight=\"0\" scrolling=\"no\" allowtransparency=\"yes\"></iframe>\n</td>\n</tr>\n</table>\n");
			sb.append("</form>\n");
			str = str.replaceAll("##content##", sb.toString());
			File f = new File(System.getProperty("user.dir") + "\\WebRoot\\" + HtmlFile + "\\" + tablename + "_list.jsp");
			BufferedWriter o = new BufferedWriter(new FileWriter(f));
			o.write(str);
			o.close();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 根据url生成静态页面
	 * 
	 * @param u
	 *            动态文件路经 如：[url]http://www.163.com/x.jsp[/url]
	 * @param path
	 *            文件存放路经如：x:\\abc\bbb.html
	 * @return
	 */
	public static boolean JspToHtmlByURL(String u, String path) {
		// 从utl中读取html存为str
		String str = "";
		try {
			URL url = new URL(u);
			URLConnection uc = url.openConnection();
			InputStream is = uc.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			while (br.ready()) {
				str += br.readLine() + "\n";

			}
			is.close();
			// 写入文件
			File f = new File(path);
			BufferedWriter o = new BufferedWriter(new FileWriter(f));
			o.write(str);
			o.close();
			str = "";
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 根据url生成静态页面
	 * 
	 * @param url
	 *            动态文件路经 如：[url]http://www.163.com/x.jsp[/url]
	 * @return d
	 */
	public static StringBuffer getHtmlTextByURL(String url) {
		// 从utl中读取html存为str
		StringBuffer sb = new StringBuffer();
		try {
			URL u = new URL(url);
			URLConnection uc = u.openConnection();
			InputStream is = uc.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			while (br.ready()) {
				sb.append(br.readLine() + "\n");
			}
			is.close();
			return sb;
		} catch (Exception e) {
			e.printStackTrace();
			return sb;
		}
	}

	/**
	 * 根据表明生成类
	 * 
	 * @return
	 */
	public static boolean TableToClass(String tablename, Map<String, String> fields) {
	//	String url = System.getProperty("user.dir") + "\\src\\local\\spring\\table\\" + tablename.substring(0, 1).toUpperCase() + tablename.substring(1).toLowerCase() + ".java";
		String url = System.getProperty("user.dir") + "\\src\\main\\java\\com\\sys\\entity\\" + tablename.substring(0, 1).toUpperCase() + tablename.substring(1).toLowerCase() + ".java";

		File f = new File(url);
		try {
			BufferedWriter o = new BufferedWriter(new FileWriter(f));
			StringBuilder sb = new StringBuilder();
			sb.append("package com.sys.entity;\n");
			sb.append("import com.sys.base.annotation.Table;\n");
			sb.append("import  com.sys.base.model.BaseEntity;\n");
			sb.append("import java.lang.*;\n");
			sb.append("import java.math.BigDecimal;\n");
			sb.append("import java.util.*;\n");
			sb.append("@Table(\""+tablename+"\")  \n");
			sb.append("public class " + tablename.substring(0, 1).toUpperCase() + tablename.substring(1).toLowerCase() + " extends BaseEntity{\n");
			sb.append("private static final long serialVersionUID = 1L;  \n");

			StringBuilder sb1 = new StringBuilder();
			Iterator i = fields.entrySet().iterator();
			while (i.hasNext()) {
				Map.Entry me = (Map.Entry) i.next();
				String key = me.getKey().toString();// .toLowerCase();
				if("id".equalsIgnoreCase(key)||"createtime".equalsIgnoreCase(key)){
					continue;
				}
				String key2 = key.substring(0, 1).toUpperCase() + key.substring(1);
				String value = me.getValue().toString();
				String key_type = getFieldType(value);
				sb.append("private " + key_type + " " + key + ";\n");
				sb1.append("public " + key_type + " get" + key2 + "() {return " + key + ";}\n");
				sb1.append("public void set" + key2 + "(" + key_type + " " + key + ") {this." + key + " = " + key + ";}\n");
			}
			sb.append(sb1);
			sb.append("}");
			o.write(sb.toString());
			o.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 生成action
	 * 
	 * @param tablename
	 * @param fields
	 * @return
	 */
	public static boolean TableToAction(String tablename) {
		String classname = getClassName(tablename) + "Action";
		String url = System.getProperty("user.dir") + "\\src\\local\\spring\\action\\" + classname + ".java";
		File f = new File(url);
		try {
			BufferedWriter o = new BufferedWriter(new FileWriter(f));
			StringBuilder sb = new StringBuilder();
			sb.append("package local.spring.action;\n");
			sb.append("import javax.servlet.http.HttpServletRequest;\n");
			sb.append("import javax.servlet.http.HttpServletResponse;\n");
			sb.append("import org.springframework.web.servlet.ModelAndView;\n");
			sb.append("public class " + classname + " {\n");
			sb.append("public ModelAndView save" + getClassName(tablename) + "(HttpServletRequest req, HttpServletResponse res, Object cmd) {return null;}\n");
			sb.append("public ModelAndView get" + getClassName(tablename) + "(HttpServletRequest req, HttpServletResponse res, Object cmd) {return null;}\n");
			sb.append("}\n");
			o.write(sb.toString());
			o.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return true;
	}

	/**
	 * 生成对应类的辅助文件
	 * 
	 * @param tablename
	 *            表名
	 * @return
	 */
	public static boolean TableToHelp(String tablename) {
		tablename = tablename.toLowerCase();

		boolean b = true;
		String beanName = tablename.substring(0, 1).toUpperCase() + tablename.substring(1);// 表对应的类名称
		String classname = getClassName(tablename) + "Help";// 对应的辅助类的名称
		String url = System.getProperty("user.dir") + "\\src\\local\\spring\\help\\" + classname + ".java";
		File f = new File(url);
		String suffix = "";
		if (f.exists()) {// 文件已存在
			suffix = "_" + new Date().getTime();
			url = System.getProperty("user.dir") + "\\src\\local\\spring\\help\\" + classname + suffix + ".java";
			f = new File(url);
			//System.out.println("文件已存在！生成新的文件：" + url);
		}
		try {
			BufferedWriter o = new BufferedWriter(new FileWriter(f));
			String content = readFileByLines(templateFilePath);// 读取模版数据
			o.write(content.replace("TableName", beanName).replace("tableName", tablename).replace("TemplateHelp", suffix.equals("") ? classname : classname + suffix));// 写入到对应的对中
			o.close();
		} catch (Exception e) {
			e.printStackTrace();
			b = false;
		}

		return b;
	}

	/**
	 * 通过表创建HBM映射文件
	 * 
	 * @param tablename
	 * @param fields
	 * @return
	 */
	public static boolean TableToXML(String tablename, Map<String, String> fields, Object type) {
		String url = System.getProperty("user.dir") + "\\src\\local\\spring\\table\\" + tablename.substring(0, 1).toUpperCase() + tablename.substring(1).toLowerCase() + ".hbm.xml";
		File f = new File(url);
		try {
			BufferedWriter o = new BufferedWriter(new FileWriter(f));
			StringBuilder sb = new StringBuilder();
			StringBuilder sb_ = new StringBuilder();
			sb.append("<?xml version=\"1.0\"?>\n");
			sb.append("<!DOCTYPE hibernate-mapping PUBLIC \"-//Hibernate/Hibernate Mapping DTD 3.0//EN\" \"http://hibernate.sourceforge.net/hibernate-mapping-3.0.dtd\">\n");
			sb.append("<hibernate-mapping package=\"local.spring.table\">\n");
			sb.append("<class name=\"local.spring.table." + tablename.substring(0, 1).toUpperCase() + tablename.substring(1).toLowerCase() + "\" table=\"" + tablename + "\" lazy=\"false\">\n");
			Iterator i = fields.entrySet().iterator();
			while (i.hasNext()) {
				Map.Entry me = (Map.Entry) i.next();
				String key = me.getKey().toString();// .toLowerCase();
				String value = me.getValue().toString();
				String key_type = getFieldType(value);
				if (key.toLowerCase() == "id" || key.toLowerCase().equals("id")) {
					if (type.equals(CreateUGU.database_type.MYSQL) || type.equals(CreateUGU.database_type.SQLSERVER)) {
						sb.append("<id column=\"" + key + "\" name=\"" + key + "\" type=\"long\">\n");
						sb.append("<generator class=\"identity\" />\n");
						sb.append("</id>\n");
					} else if (type.equals(CreateUGU.database_type.ORACLE)) {
						sb.append("<id column=\"" + key + "\" name=\"" + key + "\" type=\"long\">\n");
						sb.append("<generator class=\"sequence\">\n");
						sb.append("<param name=\"sequence\">" + tablename + "_seq</param>\n");
						sb.append("</generator>\n");
						sb.append("</id>");
					}
				} else {
					sb_.append("<property name=\"" + key + "\" column=\"" + key + "\" type=\"" + (key_type == "Date" ? "timestamp" : (key_type.toLowerCase().equalsIgnoreCase("bigdecimal") ? "big_decimal" : key_type.toLowerCase())) + "\" />\n");
				}

			}
			sb.append(sb_.toString());
			sb.append("</class>\n");
			sb.append("</hibernate-mapping>");
			o.write(sb.toString());
			o.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean UpdateProperties(String tablename) {
		Properties prop = new Properties();// 属性集合对象
		try {
			String url = System.getProperty("user.dir") + "\\src\\";
			FileInputStream fis = new FileInputStream(url + "HibernateTable.properties");// 属性文件输入流
			prop.load(fis);// 将属性文件流装载到Properties对象中
			fis.close();// 关闭流
			String new_table_name = tablename.substring(0, 1).toUpperCase() + tablename.substring(1).toLowerCase();
			String tablenames = prop.getProperty("tableNames");
			if (("," + tablenames + ",").indexOf("," + new_table_name + ",") == -1) {
				//System.out..println("tablename:"+tablenames+","+new_table_name);
				prop.setProperty("tableNames", tablenames + "," + new_table_name);
				FileOutputStream fos = new FileOutputStream(url + "HibernateTable.properties");
				prop.store(fos, "Copyright (c) Boxcode Studio");
				fos.close();// 关闭流
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public static boolean UpdateSpring(String tablename) {
		try {
			String url = System.getProperty("user.dir") + "\\WebRoot\\WEB-INF\\";
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			DOMImplementation impl = builder.getDOMImplementation();
			// DocumentType type = impl.createDocumentType("beans",
			// "-//SPRING//DTD BEAN//EN",
			// "http://www.springframework.org/dtd/spring-beans.dtd");
			// impl.createDocumentType(qualifiedName, publicId, systemId)
			Document document = builder.parse(new File(url + "spring.xml"));
			Element brandElement = document.createElement("prop");
			brandElement.setAttribute("key", "**/" + tablename + ".do");
		//	brandElement.setTextContent(tablename);
			Element propsElement = (Element) document.getElementsByTagName("props").item(0);
			propsElement.appendChild(brandElement);
			Element firstElement = (Element) document.getElementsByTagName("beans").item(0);

			Element brandElement1 = document.createElement("bean");
			brandElement1.setAttribute("id", tablename);
			brandElement1.setAttribute("class", "org.springframework.web.servlet.mvc.multiaction.MultiActionController");
			firstElement.appendChild(brandElement1);

			Element brandElement2 = document.createElement("property");
			brandElement2.setAttribute("name", "methodNameResolver");
			brandElement1.appendChild(brandElement2);

			Element brandElement2_1 = document.createElement("ref");
			brandElement2_1.setAttribute("bean", "paramResolver");
			brandElement2.appendChild(brandElement2_1);

			Element brandElement3 = document.createElement("property");
			brandElement3.setAttribute("name", "delegate");
			brandElement1.appendChild(brandElement3);

			Element brandElement3_1 = document.createElement("ref");
			brandElement3_1.setAttribute("bean", tablename + "Delegate");
			brandElement3.appendChild(brandElement3_1);

			Element brandElement4 = document.createElement("bean");
			brandElement4.setAttribute("id", tablename + "Delegate");
			brandElement4.setAttribute("class", "local.spring.action." + getClassName(tablename) + "Action");
			firstElement.appendChild(brandElement4);
			// document.appendChild(type);
			// 保存xml文件
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource domSource = new DOMSource(document);
			// 设置编码类型
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			transformer.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, document.getDoctype().getPublicId());
			transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, document.getDoctype().getSystemId());
			StreamResult result = new StreamResult(new FileOutputStream(url + "spring.xml"));
			// 把DOM树转换为xml文件
			transformer.transform(domSource, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	private static String getFieldType(String type) {
		String result = "";
		type = type.toLowerCase();
		if (type.indexOf("int") >= 0 || type.indexOf("number") >= 0) {
			result = "Long";
		} else if (type.indexOf("varchar") >= 0 || type.indexOf("text") >= 0) {
			result = "String";
		} else if (type.indexOf("datetime") >= 0 || type.indexOf("timestamp") >= 0 || type.indexOf("date") >= 0 || type.indexOf("smalldatetime") >= 0) {
			result = "Date";
		} else if (type.indexOf("float") >= 0 || type.indexOf("double") >= 0 || type.indexOf("numeric") >= 0) {
			result = "Double";
		} else if (type.indexOf("decimal") >= 0) {
			result = "BigDecimal";
		} else {
			//System.out.println("------未知类型:" + type);
			result = "String";
		}

		return result;
	}

	/**
	 * 以行为单位读取文件，常用于读面向行的格式化文件
	 * 
	 * @param fileName
	 *            文件名
	 */
	public static String readFileByLines(String fileName) {
		File file = new File(fileName);
		BufferedReader reader = null;
		StringBuffer sb = new StringBuffer();

		try {
			//System.out..println("以行为单位读取文件内容，一次读一整行：");
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			int line = 1;
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				// 显示行号
				//System.out..println("line " + line + ": " + tempString);
				sb.append(tempString);
				sb.append("\r\n");
				line++;
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
		return sb.toString();
	}

	/**
	 * 读取文件内容
	 * 
	 * @param path
	 *            文件路径
	 * @return
	 * @throws IOException
	 */
	public static String BufferedReaderDemo(String path) throws IOException {
		File file = new File(path);
		if (!file.exists() || file.isDirectory()) {
			throw new FileNotFoundException();
		}
		BufferedReader br = new BufferedReader(new FileReader(file));
		String temp = null;
		StringBuffer sb = new StringBuffer();
		temp = br.readLine();
		while (temp != null) {
			sb.append(temp + " ");
			temp = br.readLine();
		}
		return sb.toString();
	}

	/**
	 * 获取类名主体部分，如table_name生成为TableName
	 * 
	 * @param tableName
	 *            表名
	 * @return
	 */
	public static String getClassName(String tableName) {
		StringBuffer re = new StringBuffer();
		String[] tmp = tableName.split("_");
		String tName = "";

		for (int i = 0; i < tmp.length; i++) {
			tName = tmp[i];
			if (tName != null && tName.length() > 0) {
				re.append(tName.substring(0, 1).toUpperCase() + tName.substring(1));
			}
		}
		return re.toString();
	}

	/**
	 * 测试main 函数
	 * 
	 * @param arg
	 */
	public static void main(String[] arg) {
		// UpdateSpring("abc");
		// String a = "timestamp(6)";
		//System.out..println(a.indexOf("timestamp"));
		//
		boolean b = TableToHelp("sp_dd_sp");
		if (b) {
			//System.out.println("完成！");
		} else {
			//System.out.println("失败！");
		}
	}
}
