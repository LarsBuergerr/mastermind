FROM hseeberger/scala-sbt:17.0.2_1.6.2_3.1.1
RUN apk add --no-cache -y sbt libxrender1 libxtst6 libxi6
WORKDIR /mastermind
ADD . /mastermind
CMD sbt run