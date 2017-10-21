public class TTTRecord {
    private String config;
    private int score;
    private int level;

    // Creates an object for TTTRecord
    /**
     * @param config
     * @param score
     * @param level
     */
    public TTTRecord(String config, int score, int level) { 
    	this.config = config;
    	this.score = score;
    	this.level = level;
    	
    };
    // Returns the configuration of a TTTRecord
    /**
     * @return
     */
    public String getConfiguration(){ 
        return config;
    };

    // Returns the score of a TTTRecord
    /**
     * @return
     */
    public int getScore(){ 
        return score;
    };

    // Returns the level of a TTTRecord
    /**
     * @return
     */
    public int getLevel(){ 
        return level;
    };

};

