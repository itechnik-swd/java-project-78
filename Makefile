setup:
	make -C app setup

run-dist:
	make -C app run-dist

start:
	make -C app start

build:
	make -C app build

install:
	make -C app install

clean:
	make -C app clean

test:
	make -C app test

report:
	make -C app report

lint:
	make -C app lint

build-run:
	make -C app build-run

.PHONY: bugit --versionild