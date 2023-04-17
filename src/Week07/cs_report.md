## Assignment03: Report on FFT

  FFT(Fast Fourier Transform) is an algorithm designed to compute the discrete Fourier transform or its inverse of a sequence. Because of its graceful form, FFT can be applied to multiple important calculations in our real lives. The following are five applications of FFT.

#### Signal Processing

  One of the most common applications of FFT is signal processing, where FFT is used to analyze the frequency content, phase, and other properties of a signal. In the real world, we usually use Nyquist sampling to get the discrete samples of the target signal. Such samples are added up by a large number of  harmonic waves. FFT can helps to analyze the signal sequence and get the frequency and phases of different components in the  signal with acceptable time cost. Then with the inverse of FFT, we can convert the signals in frequency domain into time domain. 

/p

/p

  If operations are applied between FFT and its inverse, the process on the signal can be realized. For example, FFT can be used to remove noise from a signal by filtering out the unwanted frequency components. FFT can also be used to estimate the power spectral density of a signal, which shows how the power of a signal is distributed over different frequencies. FFT can also be used to perform time-frequency analysis, such as short-time Fourier transform, which allows for simultaneous analysis of a signal in both time and frequency domains. These techniques can be applied to various types of signals, such as audio, speech, radar, communication, and sensor data.

Another application of FFT is image processing, where FFT is used to transform an image from the spatial domain to the frequency domain. This can be useful for filtering and image compression. For example, FFT can be used to apply low-pass or high-pass filters to an image by modifying the frequency components of the image. FFT can also be used to compress an image by discarding the less significant frequency components and keeping only the most important ones. This can reduce the size of an image without losing much quality.

A third application of FFT is solving partial differential equations (PDEs), which are equations that involve derivatives of an unknown function with respect to multiple variables. PDEs are widely used in physics and mathematics to model various phenomena, such as heat transfer, fluid dynamics, electromagnetism, and quantum mechanics. However, solving PDEs analytically can be very difficult or impossible in many cases. Therefore, numerical methods are often used to approximate the solution of PDEs using discrete values. One such method is the spectral method, which uses FFT to transform a PDE from the physical domain to the spectral domain, where it becomes easier to solve.

A fifth application of FFT is number-theoretic transform (NTT), which is a variant of FFT that works over finite fields instead of complex numbers. NTT has applications in cryptography and computer algebra, where it can be used to perform fast multiplication and convolution of large integers or polynomials. For example, NTT can be used to implement fast algorithms for modular exponentiation and discrete logarithm, which are essential for public-key cryptography schemes such as RSA and Diffie-Hellman.

These are some of my favorite applications of FFT that I have learned by myself. I hope this report has demonstrated my understanding and appreciation of this powerful algorithm and its diverse applications.