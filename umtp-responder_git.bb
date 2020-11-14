# Recipe created by krashid@testo.de
# This is the basis to integrate uMTP Responder code in Yocto Build

LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://LICENSE;md5=d32239bcb673463ab874e80d47fae504"

SRC_URI = "git://github.com/viveris/uMTP-Responder.git;protocol=https \
	   file://umtprd.conf \
	   file://umtprd-ffs.sh \
	"

# Modify these as desired
PV = "1.0+git${SRCPV}"
SRCREV = "52c38a4a756b30efecc6470b5e4d4f22f9187a45"

S = "${WORKDIR}/git"

do_configure () {
	# Specify any needed configure commands here
	:
}

do_compile () {
	# You will almost certainly need to add additional arguments here
	oe_runmake
}

do_install () {
	install -d ${D}${sysconfdir}/init.d
	install -m 0755 ${WORKDIR}/umtprd.conf ${D}${sysconfdir}/init.d/umtprd.conf
	install -d ${D}/home/root
        install -m 0755 ${S}/umtprd ${D}/home/root/umtprd
	install -m 0755 ${WORKDIR}/umtprd-ffs.sh ${D}/home/root/umtprd-ffs.sh
}

FILES_${PN} += "/home/root"
