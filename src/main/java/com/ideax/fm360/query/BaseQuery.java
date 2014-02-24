package com.ideax.fm360.query;

/**
 * @author xxjacob
 */
public class BaseQuery {
	protected Integer pageSize = 0;
	protected Integer startRow = 0;//起始行
	protected Integer endRow;//结束行(闭合)
	protected Integer page;
	public Integer getStartRow() {
		this.startRow = (page-1)*this.pageSize;
		this.endRow= this.startRow + this.pageSize - 1;
		return startRow;
	}
	public Integer getEndRow() {
		return endRow;
	}
	public BaseQuery setEndRow(Integer endRow) {
		this.endRow = endRow;
		return this;
	}
	public BaseQuery setPage(Integer page) {
		this.page = page;
		return this;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public BaseQuery setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
		return this;
	}
	public Integer getPage() {
		return page;
	}
}
