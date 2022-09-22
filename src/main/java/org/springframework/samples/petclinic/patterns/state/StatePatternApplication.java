package org.springframework.samples.petclinic.patterns.state;

import org.jpatterns.gof.StatePattern;
@StatePattern
public class StatePatternApplication {
    
    @StatePattern.State
    interface SpeedLevel {
        void rotate(FanWallControl fanWallControl);
    }

    @StatePattern.Context
    static class FanWallControl {
        private SpeedLevel current;

        public FanWallControl() {
            current = new Off();
        }

        public void set_state(SpeedLevel state) {
            current = state;
        }

        public void rotate() {
            current.rotate(this);
        }

        @Override
        public String toString() {
            return String.format("Fan Wall Control [current = %s]", current);
        }
    }    
    
    @StatePattern.ConcreteState
    static class Off implements SpeedLevel {
        public void rotate(FanWallControl fanWallControl) {
            fanWallControl.set_state(new SpeedLevel1());
        }
    }
    
    @StatePattern.ConcreteState
    static class SpeedLevel1 implements SpeedLevel {
        public void rotate(FanWallControl fanWallControl) {
            fanWallControl.set_state(new SpeedLevel2());
        }
    }
    
    @StatePattern.ConcreteState
    static class SpeedLevel2 implements SpeedLevel {
        public void rotate(FanWallControl fanWallControl) {
            fanWallControl.set_state(new SpeedLevel3());
        }
    }
    
    @StatePattern.ConcreteState
    static class SpeedLevel3 implements SpeedLevel {
        public void rotate(FanWallControl fanWallControl) {
            fanWallControl.set_state(new Off());
        }
    }
}
