package com.iteratrlearning.shu_book.chapter_06;

public class Twoot {
    private final String id;
    private final String senderId;
    private final String content;
	
    public Twoot(final String id, final String senderId, final String content) {
    	this.id = id;
        this.senderId = senderId;
        this.content = content;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Twoot twoot = (Twoot) o;

        return id.equals(twoot.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
