package qloots.project.devapologiesrandomizer.utils;

import org.springframework.stereotype.Service;

@Service
public class RandomUtil {

    public int getRandomInteger(int maximum, int minimum){
        return ((int) (Math.random()*(maximum - minimum))) + minimum;
    }
}
