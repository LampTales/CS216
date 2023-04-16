#include <string.h>
#include <cmath>
#include <iostream>
#define MAX 1048576
using namespace std;

struct complex {
    double r;
    double i;
    complex(double r = 0, double i = 0) {
        this->r = r;
        this->i = i;
    }
    complex operator+(const complex x) {
        return complex(this->r + x.r, this->i + x.i);
    }
    complex operator-(const complex x) {
        return complex(this->r - x.r, this->i - x.i);
    }
    complex operator*(const complex x) {
        return complex(this->r * x.r - this->i * x.i, this->r * x.i + this->i * x.r);
    }
};
complex fc[MAX], gc[MAX];
int F[MAX], G[MAX];
double H[MAX / 2];
int len = 1;
double PI = acos(-1);

void FFT(complex* com, int* rev, int op) {
    for (int i = 0; i < len; i++) {
        if (i < rev[i]) {
            complex temp = com[i];
            com[i] = com[rev[i]];
            com[rev[i]] = temp;
        }
    }

    for (int t = 1; t < len; t <<= 1) {
        complex wn = complex(cos(PI * op / t), sin(PI * op / t));
        for (int i = 0; i < len; i += 2 * t) {
            complex w = complex(1, 0);
            for (int j = 0; j < t; j++) {
                complex x = com[i + j];
                complex y = w * com[i + j + t];
                com[i + j] = x + y;
                com[i + j + t] = x - y;
                w = w * wn;
            }
        }
    }
    if (op == -1) {
        for (int i = 0; i < len; i++) {
            com[i].r = com[i].r / len;
        }
    }
}

bool is_zero(double x) {
    return (0.2 > x && x > -0.2);
}

int main() {
    freopen("input.txt", "r", stdin);
    char str[500005];
    scanf("%s", str);
    int n = strlen(str);
    int time = 0;
    while (len < 2 * n) {
        len = len << 1;
        time++;
    }
    int rev[len];
    rev[0] = 0;
    for (int i = 0; i < len; i++) {
        rev[i] = (rev[i >> 1] >> 1) | ((i & 1) << (time - 1));
    }

    // int F[len] = {0};
    // int G[len] = {0};
    // printf("*\n");
    for (int i = 0; i < n; i++) {
        switch (str[i]) {
            case '?':
                F[i] = 0;
                G[n - 1 - i] = 0;
                break;
            case '0':
                F[i] = 1;
                G[n - 1 - i] = 1;
                break;
            case '1':
                F[i] = 2;
                G[n - 1 - i] = 2;
                break;
        }
    }

    printf("%d\n", len);

    for (int i = 0; i < len; i++) {
        fc[i] = complex();
        gc[i] = complex();
    }
    // double H[n / 2] = {0.0};

    for (int i = 0; i < len; i++) {
        fc[i].r = F[i] * F[i] * F[i];
        gc[i].r = G[i];
    }
    FFT(fc, rev, 1);
    FFT(gc, rev, 1);
    for (int i = 0; i < len; i++) {
        fc[i] = fc[i] * gc[i];
    }
    FFT(fc, rev, -1);
    for (int i = 0; i < n / 2; i++) {
        H[i] += fc[i].r;
    }

    for (int i = 0; i < len; i++) {
        fc[i].r = F[i] * F[i];
        fc[i].i = 0;
        gc[i].r = -2 * G[i] * G[i];
        gc[i].i = 0;
    }
    FFT(fc, rev, 1);
    FFT(gc, rev, 1);
    for (int i = 0; i < len; i++) {
        fc[i] = fc[i] * gc[i];
    }
    FFT(fc, rev, -1);
    for (int i = 0; i < n / 2; i++) {
        H[i] += fc[i].r;
    }

    for (int i = 0; i < len; i++) {
        fc[i].r = F[i];
        fc[i].i = 0;
        gc[i].r = G[i] * G[i] * G[i];
        gc[i].i = 0;
    }
    FFT(fc, rev, 1);
    FFT(gc, rev, 1);
    for (int i = 0; i < len; i++) {
        fc[i] = fc[i] * gc[i];
    }
    FFT(fc, rev, -1);
    for (int i = 0; i < n / 2; i++) {
        H[i] += fc[i].r;
    }

    long ans = is_zero(H[0]) ? 1 : 0;
    for (int i = 1; i < n / 2; i++) {
        ans = ans ^ ((is_zero(H[i]) ? 1 : 0) * ((long)(i + 1)) * ((long)(i + 1)));
    }
    printf("%ld\n", ans);
}