package tn.esprit.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.entities.Rayon;
import tn.esprit.spring.repository.RayonRepository;

@Service
@Slf4j
public class IRayonServiceImpl implements IRayon {
	@Autowired
	RayonRepository rayonRepository;
	@Override
	public List<Rayon> retrieveAllRayons() {
		List<Rayon> rayons = (List<Rayon>) rayonRepository.findAll();
		return rayons;
	}

	@Override
	public Rayon addRayon(Rayon r) {
		rayonRepository.save(r);
		return r;
	}

	@Override
	public void deleteRayon(Long id) {
		rayonRepository.deleteById(id);
	}

	@Override
	public Rayon updateRayon(Rayon u) {
		return rayonRepository.save(u);
	}

	@Override
	public Rayon retrieveRayon(Long id) {
		Rayon rayon = rayonRepository.findById(id).orElse(null);
		return rayon;	// TODO Auto-generated method stub
	}



}
