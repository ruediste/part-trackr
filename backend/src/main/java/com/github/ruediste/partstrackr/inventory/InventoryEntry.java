package com.github.ruediste.partstrackr.inventory;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.github.ruediste.partstrackr.location.Location;
import com.github.ruediste.partstrackr.part.Part;

@Entity
public class InventoryEntry {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long id;

	@ManyToOne(optional = true)
	public Part part;

	@ManyToOne(optional = true)
	public Location location;

	@OneToMany(mappedBy = LocationParameterValue_.ENTRY, fetch = FetchType.LAZY)
	public List<LocationParameterValue> parameterValues;

	public int count;

	public String parameterValuesDescription() {
		return parameterValues.stream().sorted(Comparator.comparing(x -> x.definition.name))
				.map(x -> x.definition.name + ": " + x.definition.format(x.value)).collect(Collectors.joining((", ")));
	}
}
