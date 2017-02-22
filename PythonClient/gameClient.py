import json
import socket
import sys

import pygame
from pygame.locals import *
import threading

def set_interval(func, sec):
    def func_wrapper():
        set_interval(func, sec)
        func()
    t = threading.Timer(sec, func_wrapper)
    t.start()
    return t


pygame.init()

this_is_heart_beat_rate_please_do_not_change_this_number_this_stupid_language_do_not_have_constant = 5
username = response = raw_input("Please enter your name: ")


def connect_to_server():
    s = socket.socket()
    host = "localhost"
    port = 12345
    s.connect((host, port))
    return s


def read_static_of_board(s):
    received = s.recv(1024)
    print received
    return json.loads(received)


def send_key_action_to_server(client, key):
    temp = {
        'username': username,
        'action': key
    }
    client.send(json.dumps(temp) + '\n')


def read_moving_object_of_board(s, ex_moving_object):
    try:
        received = s.recv(1024)
        return json.loads(received), True
    except:
        return ex_moving_object, False


def start_sending_heart_beat_to_server():
    temp = {
        'username': username,
        'message': 'HeartBeat'
    }
    temp = json.dumps(temp)
    heart_beat_socket = connect_to_server()

    def heart_beat_generator():
        heart_beat_socket.send(temp + '\n')

    set_interval(heart_beat_generator,
                 this_is_heart_beat_rate_please_do_not_change_this_number_this_stupid_language_do_not_have_constant)


def draw_map():
    def draw_table(map_len, map_length):
        black = (0, 0, 0)
        screen_color = (229, 189, 2)
        screen = pygame.display.set_mode((map_length, map_length))
        screen.fill(screen_color)

        # outers lines
        pygame.display.update()
        pygame.draw.lines(screen, black, True, [(0, 0), (map_length, 0)], 3)
        pygame.draw.lines(screen, black, True, [(0, 0), (0, map_length)], 3)
        pygame.draw.lines(screen, black, True, [(0, map_length), (map_length, map_length)], 5)
        pygame.draw.lines(screen, black, True, [(map_length, 0), (map_length, map_length)], 5)
        pygame.display.update()

        i = 0
        while i <= map_length:  # vertical lines
            pygame.draw.lines(screen, black, True, [(i, 0), (i, map_length)], 2)
            i += map_length / map_len
        i = 0
        while i <= map_length:  # horizontal lines
            pygame.draw.lines(screen, black, True, [(0, i), (map_length, i)], 2)
            i += map_length / map_len  # 30
        pygame.display.update()
        return screen

    def draw_obstacles(map_len, screen, obstacles, map_length):
        obstacle_color = (229, 44, 2)
        for obstacle in obstacles:
            pygame.draw.rect(screen, obstacle_color,
                             (obstacle[1] * map_length / map_len + 2, obstacle[0] * map_length / map_len + 2,
                              map_length / map_len - 2, map_length / map_len - 2))
        pygame.display.update()

    def draw_snake(map_len, screen, snake, map_length):
        snake_color = (0, 0, 0)
        for pos in snake:
            pygame.draw.rect(screen, snake_color, (pos[1] * map_length / map_len + 2, pos[0] * map_length / map_len + 2,
                                                   map_length / map_len - 2, map_length / map_len - 2), 0)
        pygame.display.update()

    def draw_food(map_len, screen, food_pos, map_length):
        food_color = (0, 166, 244)
        pygame.draw.rect(screen, food_color,
                         (food_pos[1] * map_length / map_len + 2, food_pos[0] * map_length / map_len + 2,
                          map_length / map_len - 2, map_length / map_len - 2))

    def removes(map_len, screen, snake_tail_pos, map_length):
        screen_color = (229, 189, 2)
        pygame.draw.rect(screen, screen_color,
                         (snake_tail_pos[1] * map_length / map_len + 2, snake_tail_pos[0] * map_length / map_len + 2,
                          map_length / map_len - 2, map_length / map_len - 2))

    def draw_initial_elements():
        screen = draw_table(map_len, map_length)
        draw_obstacles(map_len, screen, obstacles_pos, map_length)
        for snake in moving_object_of_board['snakes']:
            draw_snake(map_len, screen, snake, map_length)
        draw_food(map_len, screen, moving_object_of_board['food'], map_length)
        pygame.display.update()
        return screen

    def update(screen):
        for snake in moving_object_of_board['snakes']:
            draw_snake(map_len, screen, snake, map_length)
        draw_food(map_len, screen, moving_object_of_board['food'], map_length)
        # for tail in moving_object_of_board['removes']:
        #     removes(map_len, screen, tail, map_length)
        pygame.display.update()

    map_length = 300
    map_len = board['size']
    obstacles_pos = board['obstacles']
    moving_object_of_board, changed = read_moving_object_of_board(client, None)

    print "sss"
    print moving_object_of_board['snakes']

    screen = draw_initial_elements()

    # become non-block
    client.setblocking(0)

    while True:
        moving_object_of_board, changed = read_moving_object_of_board(client, moving_object_of_board)
        # aaaa = client.recv(1024)
        # print aaaa
        if changed:
            update(screen)

        for event in pygame.event.get():
            if event.type == QUIT:
                pygame.quit()
                client.close()
                sys.exit()
            elif event.type == KEYDOWN:
                if event.key == pygame.K_LEFT:
                    send_key_action_to_server(client, "left")
                    print "key left."
                elif event.key == pygame.K_RIGHT:
                    send_key_action_to_server(client, "right")
                    print "key right."
                elif event.key == pygame.K_UP:
                    send_key_action_to_server(client, "up")
                    print "key up."
                elif event.key == pygame.K_DOWN:
                    send_key_action_to_server(client, "down")
                    print "key down."

start_sending_heart_beat_to_server()
client = connect_to_server()
board = read_static_of_board(client)
draw_map()
