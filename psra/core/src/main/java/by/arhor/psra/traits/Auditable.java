package by.arhor.psra.traits;

import java.time.LocalDateTime;

public interface Auditable {

  LocalDateTime getDateTimeCreated();

  LocalDateTime getDateTimeUpdated();

}
