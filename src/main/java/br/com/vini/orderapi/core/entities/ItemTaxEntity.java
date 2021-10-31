package br.com.vini.orderapi.core.entities;

import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.vini.orderapi.api.service.fiscalcategory.protocol.ItemTaxProtocol;

/**
 * Item fiscal com informações de taxas fiscais (impostos)
 * 
 * @author viniciosarodrigues
 *
 */
@Entity
@Table(name = "ITEM_FISCAL")
public class ItemTaxEntity extends BaseEntity<Long, ItemTaxProtocol> {

    private static final long serialVersionUID = -3232831481016799918L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "DESCRICAO")
    private String name;

    @Column(name = "PERCENTUAL")
    private BigDecimal percentual;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPercentual() {
        return percentual;
    }

    public void setPercentual(BigDecimal percentual) {
        this.percentual = percentual;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, percentual);
    }

    @Override
    public ItemTaxProtocol getProtocol() {
        ItemTaxProtocol protocol = new ItemTaxProtocol();
        protocol.setId(this.id);
        protocol.setName(this.name);
        protocol.setPercentual(this.percentual);
        return protocol;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ItemTaxEntity other = (ItemTaxEntity) obj;
        return Objects.equals(id, other.id) && Objects.equals(name, other.name) && Objects.equals(percentual, other.percentual);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ItemTaxEntity [id=");
        builder.append(id);
        builder.append(", name=");
        builder.append(name);
        builder.append(", percentual=");
        builder.append(percentual);
        builder.append("]");
        return builder.toString();
    }

}
