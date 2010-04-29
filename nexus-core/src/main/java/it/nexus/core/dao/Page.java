package it.nexus.core.dao;

/**
 * Created by IntelliJ IDEA.
 * User: dcriori
 * Date: 2010-4-28
 * Time: 14:41:31
 * 分页时使用的类
 */
public class Page {
  //-- 公共变量 --//
  public static final String ASC = "asc";
  public static final String DESC = "desc";

  private int cpage;      //当前页
  private long total;     //总页数
  private String url;     //url地址
  private int pageSize = 15;   //一页最大记录数
  private int firstResult = 0; //记录起始位置
  private long totalCount = 0;  //总记录数

  public long getTotalCount() {
    return totalCount;
  }

  public void setTotalCount(long totalCount) {
    this.totalCount = totalCount;
  }

  public int getFirstResult() {
    return (cpage - 1) * pageSize;
  }

  public void setFirstResult(int firstResult) {
    this.firstResult = firstResult;
  }

  public Page() {
  }

  public Page(int pageSize) {
    this.pageSize = pageSize;
  }

  public int getPageSize() {
    return pageSize;
  }

  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public long getTotal() {
    if (totalCount < 0) {
      return -1;
    }

    long count = totalCount / pageSize;
    if (totalCount % pageSize > 0) {
      count++;
    }
    return count;
  }

  public void setTotal(long total) {
    this.total = total;
  }

  public int getCpage() {
    return cpage;
  }

  public void setCpage(int cpage) {
    this.cpage = cpage;
  }
}
