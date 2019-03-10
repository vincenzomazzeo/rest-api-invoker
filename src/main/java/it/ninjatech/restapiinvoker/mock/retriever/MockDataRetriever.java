package it.ninjatech.restapiinvoker.mock.retriever;

import it.ninjatech.restapiinvoker.mock.data.AbstractMockData;

public interface MockDataRetriever<T> {

	public AbstractMockData<T> retrieve();

}
