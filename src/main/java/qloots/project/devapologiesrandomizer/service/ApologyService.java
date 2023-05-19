package qloots.project.devapologiesrandomizer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import qloots.project.devapologiesrandomizer.entity.Apology;
import qloots.project.devapologiesrandomizer.repository.ApologyRepository;
import qloots.project.devapologiesrandomizer.utils.RandomUtil;

import java.util.List;
import java.util.Optional;

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

    public Apology getRandomApology() {
        List<Apology> allApologies = getAllApologies();
        if (!allApologies.isEmpty()) {
            Optional<Apology> firstApology = allApologies.stream().findFirst();
            int min = firstApology.get().getHttpCode();
            int max = min + allApologies.size();

            int randomApologyId = randomUtil.getRandomInteger(max, min);

            Apology apology = getApologyByHttpCode(randomApologyId);
            if (apology != null) {
                return apology;
            }
        }
        Apology apology = new Apology();
        apology.setHttpCode(418);
        apology.setTag("NOT IN DATABASE");
        apology.setMessage("I am a Tea Pot");
        return apology;
    }

    public Apology createApology(Apology apology) {
        return apologyRepository.save(apology);
    }

    public Apology getApologyByHttpCode(int httpCode) {
        return apologyRepository.findByHttpCode(httpCode);
    }
}
