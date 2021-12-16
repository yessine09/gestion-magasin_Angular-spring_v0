package tn.esprit.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.entities.DetailProduit;
import tn.esprit.spring.repository.DetailProduitRepository;

@Service
@Slf4j
public class IDetailProduitServiceImpl implements IDetailProduit {
	@Autowired
	DetailProduitRepository detailProduitRepository;
	@Override
	public List<DetailProduit> retrieveAllDetailProduits() {
		List<DetailProduit> detailproduits = (List<DetailProduit>) detailProduitRepository.findAll();
		return detailproduits;
	}

	@Override
	public DetailProduit addDetailProduit(DetailProduit dp) {
		detailProduitRepository.save(dp);
		return dp;
	}

	@Override
	public void deleteDetailProduit(Long id) {
		detailProduitRepository.deleteById(id);
		
	}

	@Override
	public DetailProduit updateDetailProduit(DetailProduit u) {
		return detailProduitRepository.save(u);
	}

	@Override
	public DetailProduit retrieveDetailProduit(Long id) {
		DetailProduit detailProduit = detailProduitRepository.findById(id).orElse(null);
		return detailProduit;	// TODO Auto-generated method stub
	}

	
}
