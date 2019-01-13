package com.sys.common;

import org.apache.ibatis.session.RowBounds;

public class PagingRounds extends RowBounds {

	public PagingRounds(int offset, int limit) {
		super(offset, limit);
	}

}
