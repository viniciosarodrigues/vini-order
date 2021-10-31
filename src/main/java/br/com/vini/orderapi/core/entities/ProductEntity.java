package br.com.vini.orderapi.core.entities;

import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.vini.orderapi.api.service.product.protocol.ProductProtocol;

/**
 * Produto para geração de pedidos na API.
 * 
 * @author viniciosarodrigues
 *
 */
@Entity
@Table(name = "PRODUTO")
public class ProductEntity extends BaseEntity<Long, ProductProtocol> {

    private static final long serialVersionUID = 2276215228479577369L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "DESCRICAO")
    private String description;

    @Column(name = "VALOR_UNITARIO")
    private BigDecimal unitValue;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(name = "ID_CATEGORIA_FISCAL")
    private FiscalCategoryEntity fiscalCategory;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getUnitValue() {
        return unitValue;
    }

    public void setUnitValue(BigDecimal unitValue) {
        this.unitValue = unitValue;
    }

    public FiscalCategoryEntity getFiscalCategory() {
        return fiscalCategory;
    }

    public void setFiscalCategory(FiscalCategoryEntity fiscalCategory) {
        this.fiscalCategory = fiscalCategory;
    }

    /**
     * Retorna um novo objeto de comunicação
     * 
     * @return Protocolo de comunicação de Produto
     */
    @Override
    public ProductProtocol getProtocol() {
        ProductProtocol protocol = new ProductProtocol();
        protocol.setId(this.id);
        protocol.setDescription(this.description);
        protocol.setUnitValue(this.unitValue);
        if (this.fiscalCategory != null)
            protocol.setCategory(this.fiscalCategory.getProtocol());
        return protocol;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fiscalCategory, id, unitValue);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ProductEntity other = (ProductEntity) obj;
        return Objects.equals(fiscalCategory, other.fiscalCategory) && Objects.equals(id, other.id)
                && Objects.equals(unitValue, other.unitValue);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ProductEntity [id=");
        builder.append(id);
        builder.append(", unitValue=");
        builder.append(unitValue);
        builder.append(", fiscalCategory=");
        builder.append(fiscalCategory);
        builder.append("]");
        return builder.toString();
    }

}
