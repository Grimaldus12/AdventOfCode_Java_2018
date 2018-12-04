class ID {
    private String id;

    ID(String id) {
        this.id = id;
    }

    String getId() {
        return id;
    }

    //returns how many chars are different given two id Strings
    int calculateDifference(ID secondID) {
        int differs = id.length();
        for (int i = 0; i < id.length(); i++) {
            if(id.charAt(i) == secondID.getId().charAt(i)) differs--;
        }
        return differs;
    }

    //returns the Index at which two ID-Strings differ
    int calculateDifferingIndex(ID secondID) {
        int index = 0;
        for (int i = 0; i < id.length(); i++) {
            if(!(id.charAt(i) == secondID.getId().charAt(i))) {
                index = i;
                break;
            }
        }
        return index;
    }
}
