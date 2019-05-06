package by.arhor.psra.repository.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "tags")
public class Tag {
	
	@Id
	@Field("id")
    private String id;
	
	@Field("name")
	@Indexed(unique = true)
    private String name;

	public Tag(String name) {
		this(null, name);
	}

}
