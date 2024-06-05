/*
 * Copyright 2012-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package software.xdev.spring.data.eclipse.store.demo.simple;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import software.xdev.spring.data.eclipse.store.repository.config.EnableEclipseStoreRepositories;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;


@SpringBootApplication
@EnableEclipseStoreRepositories
public class DemoApplication implements CommandLineRunner
{
	private static final Logger LOG = LoggerFactory.getLogger(DemoApplication.class);
	private final DataItemRepository repository;
	
	public DemoApplication(final DataItemRepository repository)
	{
		this.repository = repository;
	}
	
	public static void main(final String[] args)
	{
		final ConfigurableApplicationContext run = SpringApplication.run(DemoApplication.class, args);
		run.close();
	}
	
	@Override
	public void run(final String... args)
	{
		UUID newRandomUUID = UUID.randomUUID();

		if(this.repository.findById(IntegerDataItem.class).isEmpty())
		{
			DataItemList<IntegerDataItem> dataItemList = new DataItemList(IntegerDataItem.class);
			dataItemList.add(new IntegerDataItem(newRandomUUID, 1));
			this.repository.save(dataItemList);
		}

		Optional<DataItemList<?>> byId = this.repository.findById(IntegerDataItem.class);
		byId.ifPresent(itemList ->
		{
			System.out.println("Found item list: " + itemList.getClass().getSimpleName());
			itemList.findById(newRandomUUID).ifPresent( item ->
				System.out.println("Found item: " + item.getId())
			);
		});
	}
}
