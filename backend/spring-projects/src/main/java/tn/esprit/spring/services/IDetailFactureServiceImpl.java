package tn.esprit.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.entities.DetailFacture;
import tn.esprit.spring.repository.DetailFactureRepository;

@Service
@Slf4j
public class IDetailFactureServiceImpl implements IDetailFacture {
	@Autowired
	DetailFactureRepository detailFactureRepository;
	@Override
	public List<DetailFacture> retrieveAllDetailFactures() {
		List<DetailFacture> detailFactures = (List<DetailFacture>) detailFactureRepository.findAll();
		return detailFactures;
	}

	@Override
	public DetailFacture addDetailFacture(DetailFacture df) {
		detailFactureRepository.save(df);
		return df;
	}

	@Override
	public void deleteDetailFacture(Long id) {
		detailFactureRepository.deleteById(id);
		
	}
	@Override
	public DetailFacture updateDetailFacture(DetailFacture u) {
		return detailFactureRepository.save(u);
	}
	

	@Override
	public DetailFacture retrieveDetailFacture(Long id) {
		DetailFacture detailFacture = detailFactureRepository.findById(id).orElse(null);
		return detailFacture;	// TODO Auto-generated method stub
	}



}
