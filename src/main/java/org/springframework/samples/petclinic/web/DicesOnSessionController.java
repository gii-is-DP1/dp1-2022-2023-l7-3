package org.springframework.samples.petclinic.web;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DicesOnSessionController {

    public static int NUM_DICES=5;
    public static int NUM_DICES_SIDES=6;

    @GetMapping("/session/rolldices")
    public @ResponseBody Integer[] rollDices(HttpSession session){
        Integer[] dices=new Integer[NUM_DICES];
        for(int i=0;i<NUM_DICES;i++)
            dices[i]=1+(int)Math.floor(Math.random()*NUM_DICES_SIDES);
        session.setAttribute("dices", dices);
        return dices;
    }

    @GetMapping("/session/sumdices")
    public @ResponseBody Integer sumDices(HttpSession session){
        Integer[] dices=(Integer[])session.getAttribute("dices");
        Integer result=0;
        for(int i=0;i<NUM_DICES;i++)
            result+=dices[i];
        return result;
    }
}
