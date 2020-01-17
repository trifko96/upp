package com.example.demonc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demonc.model.ScientificArea;
import com.example.demonc.repository.ScientificAreaRepository;

@Service
public class ScientificAreaServiceImpl implements ScientificAreaService {
	
	@Autowired
	private ScientificAreaRepository areaRepository;
	
	@Override
	public List<ScientificArea> getAll() {
		// TODO Auto-generated method stub
		return areaRepository.findAll();
	}

	@Override
	public ScientificArea save(ScientificArea area) {
		// TODO Auto-generated method stub
		return areaRepository.save(area);
	}

}
