Yocto Recipes for the Printer Application Framework (PAPPL)
===========================================================

This repository contains [Yocto][1] recipes for building [PAPPL][2] for your
embedded Linux printing projects.  PAPPL is a simple C-based framework/library
for developing CUPS Printer Applications, which are the recommended replacement
for printer drivers.  When used in an embedded Linux environment, it is possible
to make existing (or new!) printers compatible with basically all mobile and
desktop operating systems with a single printer application.

PAPPL also supports the Linux USB gadget API so that your embedded device can be
connected to a desktop computer and show up as a regular USB printer.


Importing Into Yocto
--------------------

Typically you will use a Git submodule to import this repository into one of
your "meta" layers, e.g.:

    cd meta-myproject
    git submodule add https://github.com/michaelrsweet/recipes-pappl.git

From there you just add a dependency on "pappl" to get PAPPL and its
dependencies added to your image(s).

> *Note:* The "master" branch of this repository tracks the current stable
> release of PAPPL...

For USB gadget support you'll need to enable `CONFIG_USB_CONFIGFS_F_PRINTER`
and `CONFIG_USB_G_PRINTER` (set to "m") in your Linux kernel "defconfig" file.


Legal Stuff
-----------

PAPPL is Copyright © 2019-2023 by Michael R Sweet.

This software is licensed under the Apache License Version 2.0 with an
(optional) exception to allow linking against GPL2/LGPL2 software (like older
versions of CUPS).  See the files "LICENSE" and "NOTICE" for more information.


[1]: https://www.yoctoproject.org
[2]: https://www.msweet.org/pappl
