package by.arhor.psra.repository.model;

import static java.util.stream.Collectors.toList;

import java.util.List;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import by.arhor.psra.CoreVersion;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true, exclude = {"teacher", "learners"})
@ToString(callSuper = true, exclude = {"teacher", "learners"})
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "courses")
public class Course extends IdentifiedEntity {
	
	private static final long serialVersionUID = CoreVersion.SERIAL_VERSION_UID;
	
	@Field("name")
	@Indexed(unique = true)
	private String name;
	
	@Field("description")
	private String description;
	
    @DBRef(lazy = true)
    private User teacher;

	@DBRef(lazy = true)
	private List<User> learners;
	
	@Override
	public Course clone() {
		var clone = new Course();
		
		clone.id = this.id;
		clone.dateTimeCreated = this.dateTimeCreated;
		clone.dateTimeUpdated = this.dateTimeUpdated;
		clone.enabled = this.enabled;
		
		clone.name = this.name;
		clone.description = this.description;
		clone.teacher = this.teacher != null
				? this.teacher.clone()
				: null;
		clone.learners = this.learners != null
				? this.learners.stream().map(User::clone).collect(toList())
				: null;
				
		return clone;
	}
	
}
