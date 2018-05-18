package nl.amc.adict.sandbox.axon.query.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(schema = "`mast`", name = "tblItems")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString
public class Item implements Serializable {

	private static final long serialVersionUID = 8637606214639083213L;

	@Id
	@Column(name = "Item_Id")
	@Setter(value = AccessLevel.NONE)
	private String id;

	@Column(name = "Omschrijving")
	private String omschrijving;
}
