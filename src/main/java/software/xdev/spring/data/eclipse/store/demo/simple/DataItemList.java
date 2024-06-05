package software.xdev.spring.data.eclipse.store.demo.simple;

import org.eclipse.serializer.reference.Lazy;
import org.eclipse.store.integrations.spring.boot.types.concurrent.Read;
import org.springframework.data.annotation.Id;
import software.xdev.spring.data.eclipse.store.repository.lazy.SpringDataEclipseStoreLazy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;


public class DataItemList<T extends DataItem>
{
	@Id
	private final Class<T> clazz;

	private Map<UUID, Lazy<T>> dataItems;

	public DataItemList(Class<T> clazz)
	{
		this.clazz = clazz;
		this.dataItems = new HashMap<>();
	}

	public Optional<T> findById(UUID uuid) {
		Lazy<T> dataItem = this.dataItems.get(uuid);
		if (dataItem == null) {
			return Optional.empty();
		}
		return Optional.of(dataItem.get());
	}

	public void add(T dataItem) {
		dataItems.put(dataItem.getId(), SpringDataEclipseStoreLazy.build(dataItem));
	}
}
