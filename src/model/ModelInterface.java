package model;

import java.sql.SQLException;
import java.util.Collection;

public interface ModelInterface<T> {
	public void doSave(T bean) throws SQLException;

	public boolean doDelete(String ...args) throws SQLException;

	public T doRetrieveByKey(String ...args) throws SQLException;
	
	public Collection<T> doRetrieveAll(String order) throws SQLException;
	
	public void doUpdate(T bean) throws SQLException;
}
