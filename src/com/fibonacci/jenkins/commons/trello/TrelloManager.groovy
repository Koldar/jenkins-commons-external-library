package com.fibonacci.jenkins.commons.trello

class TrelloManager {

    private String key;
    private String token;
    private String username;

    TrelloManager(String username, String key, String token) {
        this.key = key
        this.token = token
        this.username = username
    }

    Iterable<String> getBoardIds() {
        // https://api.trello.com/1/members/me/boards?key=61f4cfe14009ec62b33fd2631e10201b&token=8ff0a650900c884eaac66b4fb223ae6824b7dfa703ee4cdd6385152cd7177d5f
    }

    Iterable<Object> getBoard(String boardId) {

    }

}