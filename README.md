# ThreadingSamples
Threading Samples for my Java classes

ThreadSample -> Basic Threading data

ThreadSync -> Basic sample using threading

ClimateReader -> Base class reading global monthly climate data for NOAA. Main function, performs a single threaded read on incrmeents by 10 years starting in 1880 until 2017

ThreadedClimate -> Uses climate reader, builds an array of 4 threads and spreads the requests across them.

AsyncSample -> Sample using CompletableFuture 
