#!/bin/bash

MODE=$1

python3 node.py A $MODE &
python3 node.py B $MODE &
python3 node.py C $MODE &
python3 node.py D $MODE &
python3 node.py E $MODE &

wait