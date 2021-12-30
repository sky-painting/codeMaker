package ${package}.domain.bo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
* Description:参考Mybatis_PageHelper的Page对象封装的DTO
* date: 2021/10/26
*
* @author fanchunshuai
* @version 1.0.0
* @since JDK 1.8
*/
public class PageBO<E> implements Serializable {

    private static final long serialVersionUID = -2470832822882514457L;
    /**
    * 页码，从1开始
    */
    private int currentPageNum;

    /**
    * 当前页的下一页
    */
    private int nextPageNum;

    /**
    * 当前页的上一页
    */
    private int prePageNum;
    /**
    * 页面大小
    */
    private int pageSize;
    /**
    * 起始行
    */
    private int startRow;
    /**
    * 末行
    */
    private int endRow;
    /**
    * 总数
    */
    private long totalRows;
    /**
    * 总页数
    */
    private int totalPages;
    /**
    * 包含count查询
    */
    private boolean count = true;
    /**
    * 分页合理化
    */
    private Boolean reasonable;
    /**
    * 当设置为true的时候，如果pagesize设置为0（或RowBounds的limit=0），就不执行分页，返回全部结果
    */
    private Boolean pageSizeZero;
    /**
    * 进行count查询的列名
    */
    private String countColumn;
    /**
    * 排序
    */
    private String orderBy;
    /**
    * 只增加排序
    */
    private boolean orderByOnly;

    private List<E> Items;


        /**
        * 针对简单场景 参数的查询map处理
        */
        private Map<String,Object> queryMap;

        /**
        * 针对复杂场景 参数的查询queryDTO对象处理
        */
        private Object queryDTO;


        public Map<String, Object> getQueryMap() {
        return queryMap;
        }

        public void setQueryMap(Map<String, Object> queryMap) {
        this.queryMap = queryMap;
        }

        public Object getQueryDTO() {
        return queryDTO;
        }

        public void setQueryDTO(Object queryDTO) {
        this.queryDTO = queryDTO;
        }

        public List<E> getItems() {
            return Items;
            }

            public PageVO setItems(List<E> items) {
                Items = items;
                return this;
                }

                public PageVO() {
                super();
                }

                public PageVO(int pageNum, int pageSize) {
                this(pageNum, pageSize, true, null);
                }

                public PageVO(int pageNum, int pageSize, boolean count) {
                this(pageNum, pageSize, count, null);
                }

                private PageVO(int pageNum, int pageSize, boolean count, Boolean reasonable) {

                if (pageNum == 1 && pageSize == Integer.MAX_VALUE) {
                pageSizeZero = true;
                pageSize = 0;
                }
                this.currentPageNum = pageNum;
                this.pageSize = pageSize;
                this.count = count;
                calculateStartAndEndRow();
                setReasonable(reasonable);
                }

                public int getNextIndex() {
                if (this.currentPageNum >= this.getTotalPages()) {
                this.nextPageNum = this.currentPageNum;
                } else {
                this.nextPageNum = this.currentPageNum + 1;
                }

                return this.nextPageNum;
                }

                public int getPreIndex() {
                if (this.currentPageNum <= 1) {
                this.prePageNum = 0;
                } else {
                this.prePageNum = this.currentPageNum - 1;
                }

                return this.prePageNum;
                }


                public int getTotalPages() {
                return totalPages;
                }

                public PageVO<E> setPages(int totalPages) {
                    this.totalPages = totalPages;
                    return this;
                    }

                    public int getEndRow() {
                    return endRow;
                    }

                    public PageVO<E> setEndRow(int endRow) {
                        this.endRow = endRow;
                        return this;
                        }

                        public int getCurrentPageNum() {
                        return currentPageNum;
                        }

                        public PageVO<E> setPageNum(int currentPageNum) {
                            //分页合理化，针对不合理的页码自动处理
                            this.currentPageNum = ((reasonable != null && reasonable) && currentPageNum <= 0) ? 1 : currentPageNum;
                            return this;
                            }

                            public int getPageSize() {
                            return pageSize;
                            }

                            public PageVO<E> setPageSize(int pageSize) {
                                this.pageSize = pageSize;
                                return this;
                                }

                                public int getStartRow() {
                                return startRow;
                                }

                                public PageVO<E> setStartRow(int startRow) {
                                    this.startRow = startRow;
                                    return this;
                                    }

                                    public long getTotalRows() {
                                    return totalRows;
                                    }

                                    public void setTotal(long totalRows) {
                                    this.totalRows = totalRows;
                                    if (totalRows == -1) {
                                    totalPages = 1;
                                    return;
                                    }
                                    if (pageSize > 0) {
                                    totalPages = (int) (totalRows / pageSize + ((totalRows % pageSize == 0) ? 0 : 1));
                                    } else {
                                    totalPages = 0;
                                    }
                                    //分页合理化，针对不合理的页码自动处理
                                    if ((reasonable != null && reasonable) && currentPageNum > totalPages) {
                                    if(totalPages!=0){
                                    currentPageNum = totalPages;
                                    }
                                    calculateStartAndEndRow();
                                    }
                                    }

                                    public Boolean getReasonable() {
                                    return reasonable;
                                    }

                                    public PageVO<E> setReasonable(Boolean reasonable) {
                                        if (reasonable == null) {
                                        return this;
                                        }
                                        this.reasonable = reasonable;
                                        //分页合理化，针对不合理的页码自动处理
                                        if (this.reasonable && this.currentPageNum <= 0) {
                                        this.currentPageNum = 1;
                                        calculateStartAndEndRow();
                                        }
                                        return this;
                                        }

                                        public Boolean getPageSizeZero() {
                                        return pageSizeZero;
                                        }

                                        public PageVO<E> setPageSizeZero(Boolean pageSizeZero) {
                                            if (pageSizeZero != null) {
                                            this.pageSizeZero = pageSizeZero;
                                            }
                                            return this;
                                            }
                                            public String getOrderBy() {
                                            return orderBy;
                                            }

                                            public <E> PageVO<E> setOrderBy(String orderBy) {
                                                this.orderBy = orderBy;
                                                return (PageVO<E>) this;
                                                    }

                                                    public boolean isOrderByOnly() {
                                                    return orderByOnly;
                                                    }

                                                    public void setOrderByOnly(boolean orderByOnly) {
                                                    this.orderByOnly = orderByOnly;
                                                    }

                                                    /**
                                                    * 计算起止行号
                                                    */
                                                    private void calculateStartAndEndRow() {
                                                    this.startRow = this.currentPageNum > 0 ? (this.currentPageNum - 1) * this.pageSize : 0;
                                                    this.endRow = this.startRow + this.pageSize * (this.currentPageNum > 0 ? 1 : 0);
                                                    }

                                                    public boolean isCount() {
                                                    return this.count;
                                                    }

                                                    public PageVO<E> setCount(boolean count) {
                                                        this.count = count;
                                                        return this;
                                                        }

                                                        /**
                                                        * 设置页码
                                                        *
                                                        * @param pageNum
                                                        * @return
                                                        */
                                                        public PageVO<E> pageNum(int pageNum) {
                                                            //分页合理化，针对不合理的页码自动处理
                                                            this.currentPageNum = ((reasonable != null && reasonable) && pageNum <= 0) ? 1 : pageNum;
                                                            return this;
                                                            }

                                                            /**
                                                            * 设置页面大小
                                                            *
                                                            * @param pageSize
                                                            * @return
                                                            */
                                                            public PageVO<E> pageSize(int pageSize) {
                                                                this.pageSize = pageSize;
                                                                calculateStartAndEndRow();
                                                                return this;
                                                                }


                                                                /**
                                                                * 设置合理化
                                                                *
                                                                * @param reasonable
                                                                * @return
                                                                */
                                                                public PageVO<E> reasonable(Boolean reasonable) {
                                                                    setReasonable(reasonable);
                                                                    return this;
                                                                    }

                                                                    /**
                                                                    * 当设置为true的时候，如果pagesize设置为0（或RowBounds的limit=0），就不执行分页，返回全部结果
                                                                    *
                                                                    * @param pageSizeZero
                                                                    * @return
                                                                    */
                                                                    public PageVO<E> pageSizeZero(Boolean pageSizeZero) {
                                                                        setPageSizeZero(pageSizeZero);
                                                                        return this;
                                                                        }

                                                                        /**
                                                                        * 指定 count 查询列
                                                                        *
                                                                        * @param columnName
                                                                        * @return
                                                                        */
                                                                        public PageVO<E> countColumn(String columnName) {
                                                                            this.countColumn = columnName;
                                                                            return this;
                                                                            }



                                                                            public String getCountColumn() {
                                                                            return countColumn;
                                                                            }

                                                                            public void setCountColumn(String countColumn) {
                                                                            this.countColumn = countColumn;
                                                                            }

                                                                            @Override
                                                                            public String toString() {
                                                                            return "PageDTO{" +
                                                                            "currentPageNum=" + currentPageNum +
                                                                            ", pageSize=" + pageSize +
                                                                            ", startRow=" + startRow +
                                                                            ", endRow=" + endRow +
                                                                            ", totalRows=" + totalRows +
                                                                            ", totalPages=" + totalPages +
                                                                            ", count=" + count +
                                                                            ", reasonable=" + reasonable +
                                                                            ", pageSizeZero=" + pageSizeZero +
                                                                            ", countColumn='" + countColumn + '\'' +
                                                                            ", orderBy='" + orderBy + '\'' +
                                                                            ", orderByOnly=" + orderByOnly +
                                                                            ", Items=" + Items +
                                                                            '}';
                                                                            }
                                                                            }
