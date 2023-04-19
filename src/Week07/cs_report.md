## Assignment03: Report on FFT

  FFT(Fast Fourier Transform) is an algorithm designed to compute the discrete Fourier transform or its inverse of a sequence. Because of its graceful form, FFT can be applied to multiple important calculations in our real lives. The following are five applications of FFT.

#### Signal Processing

  One of the most common applications of FFT is signal processing, where FFT is used to analyze the frequency content, phase, and other properties of a signal. In the real world, we usually use Nyquist sampling to get the discrete samples of the target signal. Such samples are added up by a large number of  harmonic waves. FFT can helps to analyze the signal sequence and get the frequency and phases of different components in the  signal with acceptable time cost. Then with the inverse of FFT, we can convert the signals in frequency domain into time domain. 

  If operations are applied between FFT and its inverse, the process on the signal can be realized. For example, if we set the phases of some components in frequency domain to zero, the noise of a signal can be removed, and we can get the main component of the signal after the inverse of FFT. The removing of noise was one of the most important applications of FFT. Back in 1970s, countries needed to analyze the samples of the signal from the ground vibration frequency, so that they can detect whether underground nuclear tests are happening in other countries. However, to get the target signal of the nuclear bomb explosion, they have to remove the noises from other ground vibrations, such as earthquakes. Applying such process may take years for the computers at that time using DFT algorithm. FFT turned this procedure into tens of minutes. This speed-up makes the detection possible.               

  In addition, the transition of FFT makes it possible to estimate the power spectral density of a signal, which shows how the power of a signal is distributed over different frequencies. It can also be used to perform time-frequency analysis, such as short-time Fourier transform, which allows for simultaneous analysis of a signal in both time and frequency domains. These techniques can be applied to various types of signals, such as audio, speech, radar, communication, and sensor data.

#### Image Process

  Another application of FFT is image processing, where FFT is used to transform an image from the spatial domain to the frequency domain. Such process is known as template operation, which applies convolution to the image(which is actually a two-dimensional matrix). We have already known that FFT can do fast polynomial product, and the format of convolution is actually very similar to fast polynomial product. So it is not surprising that FFT is the core part of the template operation. Such operation can be useful for multiple image processing. For example, it can be used to apply low-pass or high-pass filters to an image by modifying the frequency components of the image, so that we can do image filtering. What is more, template operation can also be used in image enhancement, feature extraction, edge recognition and so on. In NN net, while doing the convolution, FFT is also applied to speed up the calculation.

  FFT can also be used to compress an image. One of the most famous and practical image compression is JPEG compression.  JPEG compression involves dividing an image into small blocks of pixels and applying a discrete cosine transform (DCT) to each block. DCT is similar to FFT, but it uses real numbers only and produces only cosine terms. DCT converts the pixel values into frequency coefficients that represent how much each cosine wave contributes to the block. By keeping only the most significant coefficients and discarding the rest, we can reduce the amount of data needed to store or transmit the image. 

#### Solving PDEs

  The third application of the FFT is to solve partial differential equations (PDEs). PDEs are equations that involve derivatives of an unknown function with respect to more than one variable. They are often used to model physical phenomena such as heat, fluid flow, waves, and electromagnetism. Solving PDEs analytically can be very difficult or impossible in many cases, so numerical methods are needed. FFT can be used to transform a PDE from the spatial domain to the frequency domain, where it may become easier to solve or approximate. The solution can then be transformed back to the spatial domain using the inverse FFT. This technique is known as the spectral method and it can achieve high accuracy and efficiency for some classes of PDEs.  For example, FFT can be used to solve the heat equation, which describes how the temperature of a body changes over time and space. By applying FFT to both sides of the equation, we can obtain an ordinary differential equation that can be solved easily.

#### Fast multiplication

  The fourth application of FFT is fast multiplication. Fast multiplication is the problem of multiplying two large numbers quickly and accurately. It has many applications in cryptography, computer algebra, or scientific computing. FFT can speed up multiplication by transforming the numbers into polynomials and multiplying them in the frequency domain. This reduces the number of operations needed from quadratic to linearithmic in the size of the numbers. The implementation of fast multiplication is very simple using FFT. As FFT can do polynomial product, we can simply take the base number as the power of the polynomial, and the sequence we obtain after the inverse FFT is the sequence of different bits in the result number.

#### NTT

  A fifth application of FFT is number-theoretic transform (NTT), which is a variant of FFT that works over finite fields instead of complex numbers. The correctness of FFT highly depends on the graceful properties of the unit root in complex complex field. In finite fields, we find out that the primordial root has the similar properties as the unit root that can support operations like FFT to work. 
$$
g_{n}^{n} \equiv 1 \space\space (mod\space p) \\\\
g_{n}^{\frac{n}{2} } \equiv -1 \space\space (mod\space p) \\\\
(g_{n}^{k + \frac{n}{2}})^{2}  \equiv g_{n}^{2k} \space\space (mod\space p)
$$
  Because of this, we can use NTT to speed up multiplication and convolution of large integers or polynomials  in cryptography and computer algebra. For example, NTT can be used to implement fast algorithms for modular exponentiation and discrete logarithm, which are essential for public-key cryptography schemes such as RSA and Diffie-Hellman.



  The above are some of the most important applications of FFT. Because of the universality of the format of FFT, it can be explained as different mathematically or physically meanings, and be applied to a large number of different calculations. FFT can certainly be considered as one of the the most world-changing algorithm in human history.













