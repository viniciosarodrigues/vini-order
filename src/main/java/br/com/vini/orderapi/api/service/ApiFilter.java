package br.com.vini.orderapi.api.service;

import java.io.Serializable;

/**
 * Filtro de busca para consultas paginadas
 * 
 * @author viniciosarodrigues
 *
 */
public abstract class ApiFilter implements Serializable {

    private static final long serialVersionUID = 4910113958612444793L;

    private Integer page = 0;
    private Integer size = 24;
    private String orderBy = "id";
    private String direction = "ASC";

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ApiFilter [page=");
        builder.append(page);
        builder.append(", size=");
        builder.append(size);
        builder.append(", orderBy=");
        builder.append(orderBy);
        builder.append(", direction=");
        builder.append(direction);
        builder.append("]");
        return builder.toString();
    }

}
