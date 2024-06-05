package software.xdev.spring.data.eclipse.store.demo.simple;

import java.util.Objects;
import java.util.UUID;


public class DataItem
{
	private final UUID id;

	public DataItem(UUID id)
	{
		this.id = id;
	}

	public UUID getId()
	{
		return id;
	}
}
