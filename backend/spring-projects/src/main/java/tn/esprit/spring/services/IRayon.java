package tn.esprit.spring.services;

import java.util.List;

import tn.esprit.spring.entities.Rayon;

public interface IRayon {
	List<Rayon> retrieveAllRayons();

	Rayon addRayon(Rayon r);

	void deleteRayon(Long id);

	Rayon updateRayon(Rayon u);

	Rayon retrieveRayon(Long id);

}
