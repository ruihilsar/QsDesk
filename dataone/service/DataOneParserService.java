package com.tristatehvac.ciright.dataone.service;

import java.util.List;

import com.tristatehvac.ciright.exception.CirightException;
import com.tristatehvac.ciright.persistence.BaseEntity;

public interface DataOneParserService {
	
	public void delete(BaseEntity obj) throws CirightException;
	
	public void save(BaseEntity obj) throws CirightException;
	
	public void load(BaseEntity obj) throws CirightException;
	
	public void update(BaseEntity obj) throws CirightException;
	
	public List<String> getVehicleDetail(String year, String manufacturer, String product, String trim) throws CirightException, Exception;
	
	public void updateDataOneGroups(long yearId, long manufacturerId, long productId, long trimId, List<String> groupList) throws CirightException;
	
}