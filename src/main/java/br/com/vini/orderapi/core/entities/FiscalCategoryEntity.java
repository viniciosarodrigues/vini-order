package br.com.vini.orderapi.core.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import br.com.vini.orderapi.api.service.fiscalcategory.protocol.FiscalCategoryProtocol;
import br.com.vini.orderapi.api.service.fiscalcategory.protocol.ItemTaxProtocol;

/**
 * Categoria fiscal de produtos. Aqui é definido informações como impostos e descrição da categoria do produto.
 * 
 * @author viniciosarodrigues
 * 
 * @see ProductEntity
 *
 */
@Entity
@Table(name = "CATEGORIA_FISCAL")
public class FiscalCategoryEntity extends BaseEntity<Long, FiscalCategoryProtocol> {

    private static final long serialVersionUID = 7415211224678421100L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "DESCRICAO")
    private String description;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "ITEM_FISCAL_E_CATEGORIA", joinColumns = {@JoinColumn(name = "ID_CATEGORIA_FISCAL")}, inverseJoinColumns = {@JoinColumn(name = "ID_ITEM_FISCAL")})
    private List<ItemTaxEntity> taxes = new ArrayList<>();

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ItemTaxEntity> getTaxes() {
        return taxes;
    }

    public void setTaxes(List<ItemTaxEntity> taxes) {
        this.taxes = taxes;
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, id);
    }

    @Override
    public FiscalCategoryProtocol getProtocol() {
        FiscalCategoryProtocol protocol = new FiscalCategoryProtocol();

        protocol.setId(this.id);
        protocol.setDescription(this.description);

        List<ItemTaxProtocol> taxes = new ArrayList<>();
        for (var itemTaxEntity : this.taxes)
            taxes.add(itemTaxEntity.getProtocol());
        protocol.setTaxes(taxes);

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
        FiscalCategoryEntity other = (FiscalCategoryEntity) obj;
        return Objects.equals(description, other.description) && Objects.equals(id, other.id);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("FiscalCategoryEntity [id=");
        builder.append(id);
        builder.append(", description=");
        builder.append(description);
        builder.append("]");
        return builder.toString();
    }

}
