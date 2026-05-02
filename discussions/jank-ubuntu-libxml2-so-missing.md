# jank — Ubuntu **`libxml2.so.2`** missing at first run

Informal recap of a thread about installing **[jank](https://github.com/jank-lang/jank)** on **Ubuntu**: **`jank -v`** failed with:

```text
jank: error while loading shared libraries: libxml2.so.2: cannot open shared object file: No such file or directory
```

## Finding which package owns a path

**Emil Aura Segerbäck** pointed at **`apt-file`** to discover which **`.deb`** provides a given filename (installed or not), with a walkthrough-style article: **[Linux Uprising — find package that provides a file](https://www.linuxuprising.com/2018/11/how-to-find-package-that-provides-file.html)**.

Typical usage pattern: `sudo apt install apt-file`, `sudo apt-file update`, then `apt-file search libxml2.so.2` (exact substring depends on what you need).

## What worked in-thread

**Ken Huang** (usually on **Fedora**, tried **Ubuntu** in a VM because **RPM** packaging for jank was not handy) resolved the loader error by creating a **symlink** after **`apt-file`** and local inspection:

```bash
sudo ln -s /usr/lib/x86_64-linux-gnu/libxml2.so.16.1.2 /usr/lib/x86_64-linux-gnu/libxml2.so.2
```

That ties the **SONAME** **`libxml2.so.2`** expected by the **jank** binary to the **versioned** library actually present on that system.

### Caveat

A manual **`libxml2.so.2`** symlink is a **workaround**: upgrades can change the versioned **`libxml2`** filename or layout; prefer whatever **`apt`** installs as the canonical **`libxml2`** runtime when it already provides **`libxml2.so.2`**. If your distro only ships a newer **`SONAME`**, upstream **jank** binaries may need **relinking** or **nix**/container installs documented by the project — verify against **[jank-lang/jank](https://github.com/jank-lang/jank)** releases and your Ubuntu version.

## Links

- [jank-lang/jank](https://github.com/jank-lang/jank)  
- [Linux Uprising — apt-file / package provides file](https://www.linuxuprising.com/2018/11/how-to-find-package-that-provides-file.html)  

Thanks to **Ken Huang**, **Emil Aura Segerbäck**, and thread participants.
