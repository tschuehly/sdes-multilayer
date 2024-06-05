package software.xdev.spring.data.eclipse.store.demo.simple;

import java.util.UUID;


public class IntegerDataItem extends DataItem
{
	private int value;

	public IntegerDataItem(UUID id, int value)
	{
		super(id);
		this.value = value;
	}

	public int getValue()
	{
		return value;
	}

	public void setValue(int value)
	{
		this.value = value;
	}
}
