package com.yc.bean;

import java.io.Serializable;
import java.util.List;

public class pagebean  implements Serializable {
private List list;

private int pageno;
private int pagesize=0;
private int prepage;
private  int nextpage;

private long total;

private int totalpage=1;

public List ListgetList() {
	return list;
}

public void setList(List list) {
	this.list = list;
}

public int getPageno() {
	return pageno;
}

public void setPageno(int pageno) {
	this.pageno = pageno;
}

public int getPagesize() {
	return pagesize;
}

public void setPagesize(int pagesize) {
	this.pagesize = pagesize;
}

public int getPrepage() {
	
	if(pageno>1) {
		
		prepage=pageno-1;
	}
	return prepage;
}

public void setPrepage(int prepage) {
	this.prepage = prepage;
}

public int getNextpage() {
	int nt=getTotalpage();
	if(pageno==nt) {
		nextpage=pageno;
	}else {
		
		nextpage=pageno+1;
	}
	return nextpage;
}

public void setNextpage(int nextpage) {
	this.nextpage = nextpage;
}

public long getTotal() {
	return total;
}

public void setTotal(long total) {
	this.total = total;
}

public int getTotalpage() {
	
	totalpage =(int)(total%pagesize==0?total/pagesize:(total/pagesize+1));
	return totalpage;
}

@Override
public String toString() {
	return "pagebean [list=" + list + ", pageno=" + pageno + ", pagesize=" + pagesize + ", prepage=" + prepage
			+ ", nextpage=" + nextpage + ", total=" + total + ", totalpage=" + totalpage + "]";
}



	
}
