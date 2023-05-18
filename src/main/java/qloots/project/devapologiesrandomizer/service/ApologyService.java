package qloots.project.devapologiesrandomizer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import qloots.project.devapologiesrandomizer.entity.Apology;
import qloots.project.devapologiesrandomizer.repository.ApologyRepository;
import qloots.project.devapologiesrandomizer.utils.RandomUtil;

import java.util.List;

@Service
public class ApologyService {

    @Autowired
    private ApologyRepository apologyRepository;

    private final RandomUtil randomUtil;

    public ApologyService(RandomUtil randomUtil) {
        this.randomUtil = randomUtil;
    }

    public List<Apology> getAllApologies() {
        return apologyRepository.findAll();
    }

    public Apology getApologyById(Long id) {
        return apologyRepository.findById(id).get();
    }

    public Apology getRandomApology() {
        List<Apology> allApologies = getAllApologies();
        int max = allApologies.size();

        Long randomApologyId = (long) randomUtil.getRandomInteger(max, 0);

        return getApologyById(randomApologyId);
    }

    public Apology createApology(Apology apology) {
        return apologyRepository.save(apology);
    }

    public Apology getApologyByHttpCode(int httpCode) {
        return apologyRepository.findByHttpCode(httpCode);
    }
}
