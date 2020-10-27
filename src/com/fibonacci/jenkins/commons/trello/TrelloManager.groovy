package com.fibonacci.jenkins.commons.trello

import com.fibonacci.jenkins.commons.rest.RestManager

class TrelloManager {

    private String key;
    private String token;
    private String username;

    TrelloManager(String username, String key, String token) {
        this.key = key
        this.token = token
        this.username = username
    }

    Iterable<Object> getBoards() {

        def boards = RestManager.getJson("https://api.trello.com/1/members/me/boards?key=${this.key}&token=${this.token}")
        return boards
    }

    Iterable<String> getBoardIds() {
        def result = new List<String>()
        for (board in getBoards()) {
            result.add(board.id)
        }
        return result
        
    }

    Object getBoard(String boardId) {
        def board = RestManager.getJson("https://api.trello.com/1/boards/${boardId}?key=${this.key}&token=${token}")
        return board
    }

    Iterable<Object> getCardsInBoard(String boardId) {
        def cards = RestManager.getJson("https://api.trello.com/1/boards/${boardId}/cards?key=${this.key}&token=${this.token}")

        return cards
    }

    Object getCardInBoard(String boardId, String cardId) {
        def card = RestManager.getJson("https://api.trello.com/1/boards/${boardId}/cards/${cardId}?key=${this.key}&token=${this.token}")
        return card
    }

    String getCardTitleInBoard(String boardId, String cardId) {
        return getCardInBoard(boardId, cardId).name
    }

    Iterable<String> getCardLabelsIdInBoard(String boardId, String cardId) {
        return getCardInBoard(boardId, cardId).idLabels
    }

}