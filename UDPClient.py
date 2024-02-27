import socket
import threading


# Adresa IP și portul serverului UDP
server_ip = '127.0.0.1'
server_port = 12345

# Crearea unui socket UDP
client_socket = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)

username = input("username: ")

# Trimite mesajul la server
firstmsg = f"{username} s-a conectat la server!"
client_socket.sendto(firstmsg.encode('utf-8'), (server_ip, server_port))

def send_messages():
    while True:
        send = input()
        if send != "":
            send = username + ': ' + send
            client_socket.sendto(send.encode('utf-8'), (server_ip, server_port))

def receive_messages():
    while True:
        # Primește răspunsul de la server
        response, server_address = client_socket.recvfrom(1024)
        data = response.decode('utf-8')
        if (data != ""):
            print(data)

# Crează două fire de execuție separate pentru trimitere și primire
send_thread = threading.Thread(target=send_messages)
receive_thread = threading.Thread(target=receive_messages)

# Porniți ambele fire de execuție
send_thread.start()
receive_thread.start()

# Așteaptă ca ambele fire de execuție să se termine (acest cod nu va fi atins niciodată)
send_thread.join()
receive_thread.join()

# Închide socketul clientului (nu se va ajunge niciodată aici în acest exemplu)
client_socket.close()





