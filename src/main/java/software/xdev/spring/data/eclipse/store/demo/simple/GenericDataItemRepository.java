package software.xdev.spring.data.eclipse.store.demo.simple;

import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

public interface GenericDataItemRepository<T> extends CrudRepository<T, UUID> {

}
