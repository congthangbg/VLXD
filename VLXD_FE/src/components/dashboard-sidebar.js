import { useEffect } from 'react';
import NextLink from 'next/link';
import { useRouter } from 'next/router';
import PropTypes from 'prop-types';
import { Box, Button, Divider, Drawer, Typography, useMediaQuery } from '@mui/material';
import OpenInNewIcon from '@mui/icons-material/OpenInNew';
import { ChartBar as ChartBarIcon } from '../icons/chart-bar';
import { Cog as CogIcon } from '../icons/cog';
import { Lock as LockIcon } from '../icons/lock';
import { Selector as SelectorIcon } from '../icons/selector';
import { ShoppingBag as ShoppingBagIcon } from '../icons/shopping-bag';
import { User as UserIcon } from '../icons/user';
import { UserAdd as UserAddIcon } from '../icons/user-add';
import { Users as UsersIcon } from '../icons/users';
import { XCircle as XCircleIcon } from '../icons/x-circle';
import ArticleIcon from '@mui/icons-material/Article';
import ComputerIcon from '@mui/icons-material/Computer';
import CameraIcon from '@mui/icons-material/Camera';
import { Logo } from './logo';
import { NavItem } from './nav-item';
import useMagicColor from './../hook/useMagicColor';
import HolidayVillageIcon from '@mui/icons-material/HolidayVillage';
import AcUnitIcon from '@mui/icons-material/AcUnit';
import SupportAgentIcon from '@mui/icons-material/SupportAgent';
import CardMembershipIcon from '@mui/icons-material/CardMembership';

const items = [
  {
    href: '/',
    icon: (<ChartBarIcon fontSize="small" />),
    title: 'Dashboard'
  },
  {
    href: '/hdx',
    icon: (<ArticleIcon fontSize="small" />),
    title: 'Hóa đơn xuất'
  },
  {
    href: '/hdn',
    icon: (<CardMembershipIcon fontSize="small" />),
    title: 'Hóa đơn nhập'
  },
  {
    href: '/customers',
    icon: (<UsersIcon fontSize="small" />),
    title: 'Khách hàng'
  },
  {
    href: '/products',
    icon: (<ComputerIcon fontSize="small" />),
    title: 'Sản phẩm'
  },
  {
    href: '/productType',
    icon: (<CameraIcon fontSize="small" />),
    title: 'Loại sản phẩm'
  },
  {
    href: '/village',
    icon: (<HolidayVillageIcon fontSize="small" />),
    title: 'Danh sách thôn'
  }, 
  {
    href: '/units',
    icon: (<AcUnitIcon fontSize="small" />),
    title: 'Danh mục đơn vị tính'
  },
  {
    href: '/supplier',
    icon: (<SupportAgentIcon fontSize="small" />),
    title: 'Nhà cung cấp'
  },

  {
    href: '/account',
    icon: (<UserIcon fontSize="small" />),
    title: 'Tài khoản'
  },
  // {
  //   href: '/settings',
  //   icon: (<CogIcon fontSize="small" />),
  //   title: 'Cài đặt'
  // },
  {
    href: '/login',
    icon: (<LockIcon fontSize="small" />),
    title: 'Đăng nhập'
  },
  // {
  //   href: '/register',
  //   icon: (<UserAddIcon fontSize="small" />),
  //   title: 'Đăng kí'
  // },
  // {
  //   href: '/404',
  //   icon: (<XCircleIcon fontSize="small" />),
  //   title: 'Error'
  // }
];

export const DashboardSidebar = (props) => {
  const { open, onClose } = props;
  const router = useRouter();
  const lgUp = useMediaQuery((theme) => theme.breakpoints.up('lg'), {
    defaultMatches: true,
    noSsr: false
  });
  useEffect(
    () => {
      if (!router.isReady) {
        return;
      }

      if (open) {
        onClose?.();
      }
    },
    // eslint-disable-next-line react-hooks/exhaustive-deps
    [router.asPath]
  );
  const color = useMagicColor();
  const content = (
    <>
      <Box
        sx={{
          display: 'flex',
          flexDirection: 'column',
          height: '100%'
        }}
      >
        <div>
          <Box sx={{ p: 3 }}>
            {/* <NextLink
              href="/"
              passHref
            >
              <a>
                <Logo
                  sx={{
                    height: 42,
                    width: 42
                  }}
                />
              </a>
            </NextLink> */}
          </Box>
          <Box sx={{ px: 2 }}>
            <Box
              sx={{
                alignItems: 'center',
                backgroundColor: 'rgba(255, 255, 255, 0.04)',
                cursor: 'pointer',
                display: 'flex',
                justifyContent: 'space-between',
                px: 3,
                py: '11px',
                borderRadius: 1
              }}
            >
              <div>
                <Typography
                  color={color}
                  variant="subtitle1"
                  fontSize="medium"
                  fontFamily="Times New Roman"
                  boxShadow="0 0 10"
                >
                  VLXD HUYỀN TOÀN
                </Typography>
               
              </div>
              <SelectorIcon
                sx={{
                  color: 'neutral.500',
                  width: 14,
                  height: 14
                }}
              />
            </Box>
          </Box>
        </div>
        <Divider
          sx={{
            borderColor: '#2D3748',
            my: 3
          }}
        />
        <Box sx={{ flexGrow: 1 }}>
          {items.map((item) => (
            <NavItem
              key={item.title}
              icon={item.icon}
              href={item.href}
              title={item.title}
            />
          ))}
        </Box>
        <Divider sx={{ borderColor: '#2D3748' }} />
        <Box
          sx={{
            px: 2,
            py: 3
          }}
        >
       
        </Box>
      </Box>
    </>
  );

  if (lgUp) {
    return (
      <Drawer
        anchor="left"
        open
        PaperProps={{
          sx: {
            backgroundColor: 'neutral.900',
            color: '#FFFFFF',
            width: 280
          }
        }}
        variant="permanent"
      >
        {content}
      </Drawer>
    );
  }

  return (
    <Drawer
      anchor="left"
      onClose={onClose}
      open={open}
      PaperProps={{
        sx: {
          backgroundColor: 'neutral.900',
          color: '#FFFFFF',
          width: 280
        }
      }}
      sx={{ zIndex: (theme) => theme.zIndex.appBar + 100 }}
      variant="temporary"
    >
      {content}
    </Drawer>
  );
};

DashboardSidebar.propTypes = {
  onClose: PropTypes.func,
  open: PropTypes.bool
};
