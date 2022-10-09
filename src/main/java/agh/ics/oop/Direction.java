package agh.ics.oop;

public enum Direction {
        FORWARD("zwierzak idzie do przodu"),
        BACKWARD("zwierzak idzie do tylu"),
        RIGHT("zwierzak idzie na prawo"),
        LEFT("zwierzak idzie na lewo");
        private String displayName;
        Direction(String displayName){
            this.displayName = displayName;
        }
        public String getDisplayName(){
            return displayName;
        }

    }
