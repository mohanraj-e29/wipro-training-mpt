url=https://cat-fact.herokuapp.com/facts/random?amount=1
MYVAR=$(curl --location --request GET $url)
echo $MYVAR