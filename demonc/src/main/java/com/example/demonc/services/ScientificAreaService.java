package com.example.demonc.services;

import java.util.List;
import org.springframework.stereotype.Service;

import com.example.demonc.model.ScientificArea;

@Service
public interface ScientificAreaService {
	public List<ScientificArea> getAll();
	public ScientificArea save(ScientificArea area);

}
