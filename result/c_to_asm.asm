datasg segment
tem db 6,7 dup  (0)
sum dw 0
a dw 0
b dw 0
c dw 0
i dw 0
T1 dw 0
T2 dw 0
T3 dw 0
T4 dw 0
T5 dw 0
T6 dw 0
T7 dw 0
datasg ends
codesg segment
assume cs:codesg,ds:datasg
start:
MOV AX,datasg
MOV DS,AX
L1: mov AX, 0
mov sum, AX
L2: mov AX, 5
mov a, AX
L3: mov AX, 4
mov b, AX
L4: mov AX, 1
mov i, AX
L5: mov AX, i
sub AX, 11
L6: jnc L19
L7: mov AX, sum
add AX, i
mov T2, AX
L8: mov AX, T2
mov sum, AX
L9: mov AX, 1
mov i, AX
L10: mov AX, i
sub AX, 11
L11: jnc L17
L12: mov AX, 1
add AX, 2
mov T4, AX
L13: mov AX, T4
mov BX,3
mul BX
mov T5, AX
L14: mov AX, T5
mov a, AX
L15: mov AX, i
add AX, 1
mov i, AX
L16: jmp L10
L17: mov AX, i
add AX, 1
mov i, AX
L18: jmp L5
L19: mov AX, b
sub AX, a
L20: jnc L28
L21: mov AX, 0
mov c, AX
L22: mov AX, c
sub AX, a
L23: jnc L26
L24: mov AX, 0
mov c, AX
L25: jmp L27
L26: mov AX, 1
mov c, AX
L27: jmp TheEnd
L28: mov AX, 1
mov c, AX
TheEnd:nop
mov ax,4c00h; int 21h的4ch号中断，安全退出程序。
int 21h;调用系统中断
codesg ends
end start
