#!/bin/bash
cd ~
wget http://files.grouplens.org/datasets/movielens/ml-20m.zip
unzip ml-20m.zip
cd ~/ml-20m/
hdfs dfs -mkdir /dataset/
hdfs dfs -put ratings.csv /dataset/
